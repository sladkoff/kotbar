package de.sldk.kotbar

import kotlin.math.roundToInt

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"

class Kotbar(private val size: Long,
             private val width: Int = 100,
             private val title: String? = null,
             private val progressBar: ProgressBar = DefaultProgressBar()) {

    private var progress = 0L
    private var progressDone = progress
    private var finished = false
    private var failure = false

    fun inc() {
        if (finished) {
            return
        }

        if (progress == 0L) {
            if (title != null) {
                System.out.println(progressBar.formatTitle(title))
            }
        }

        progress++
        progressDone++
        drawProgressBar()
    }

    fun done() {
        if (!finished) {
            print("\n")
            finished = true
        }
    }

    fun fail() {
        if (!finished) {
            progress = size
            failure = true;
            drawProgressBar()
            done()
        }
    }

    private fun drawProgressBar() {

        val decimalProgress = progress.toDouble().div(size)

        val percentage = decimalProgress.times(100).roundToInt()
        val finished = percentage == 100

        val status = when {
            failure -> ProgressStatus.FAILED
            finished -> ProgressStatus.DONE
            else -> ProgressStatus.IN_PROGRESS
        }

        val progressedBlocks = decimalProgress.times(width).roundToInt()
        val remainingBlocks = width - progressedBlocks
        val color = progressBar.getColor(status)

        val bar = with(StringBuilder("\r")) {
            append(color)
            append("$percentage%".padEnd(4))
            append(" ")
            progressBar.getBorders()?.first?.let { append(it) }
            for (b in 1..(if (finished) progressedBlocks else progressedBlocks - 1)) {
                append(progressBar.getDoneBlock())
            }

            if (!finished) {
                append(progressBar.getArrowBlock())
            }

            for (b in 1..remainingBlocks) append(progressBar.getRemainingBlock())

            progressBar.getBorders()?.second?.let { append(it) }

            append(" ").append(progressBar.getInfoBlock(percentage, progressDone, size, status))
            append(ANSI_RESET)
        }.toString()

        System.out.print(bar)
    }

}

inline fun <T> Iterable<T>.forEachWithProgressBar(action: (T) -> Unit) {
    val kotbar = Kotbar(count().toLong())

    try {
        forEach {
            action.invoke(it)
            kotbar.inc()
        }
        kotbar.done()
    } catch (e: Exception) {
        kotbar.fail()
        kotbar.done()
        throw e
    }
}