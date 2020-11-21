package hello

import java.nio.file.Path

import zio.App
import zio.URIO
import zio.cli.HelpDoc.Span.text
import zio.cli._
import zio.console.{ putStrLn, Console }

object Main extends App {

  val bytesFlag: Options[Boolean] = Options.bool("c", true).alias("c")
  val linesFlag: Options[Boolean] = Options.bool("l", true).alias("l")
  val wordsFlag: Options[Boolean] = Options.bool("w", true).alias("w")
  val charFlag: Options[Boolean]  = Options.bool("m", false).alias("m")

  case class WcOptions(bytes: Boolean, lines: Boolean, words: Boolean, char: Boolean)

  val options = (bytesFlag :: linesFlag :: wordsFlag :: charFlag).as(WcOptions)

  val args = Args.file(Exists.Yes).repeat1

  val wc = Command("wc", options, args)

  val execute: (WcOptions, ::[Path]) => URIO[Console, Unit] = (opts, paths) => putStrLn(s"${opts} x ${paths}")

  val wcApp = CLIApp(
    "ZIO Word Count",
    "0.1.2",
    text("counts words in the file"),
    wc,
    execute.tupled
  )

  override def run(args: List[String]) =
    wcApp.run(args)

}
