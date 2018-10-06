package de.sldk.kotbar

class DefaultProgressBar(private val primaryColor: String = ANSI_YELLOW) : ProgressBar {

    override fun getInfoBlock(percentage: Int, progress: Long, size: Long, status: ProgressStatus): String {
        val icon = when (status) {
            ProgressStatus.IN_PROGRESS -> ""
            ProgressStatus.FAILED -> "\u26A0"
            ProgressStatus.DONE -> "\u2713"
        }.padEnd(1)

        return "$icon $progress/$size"
    }

    override fun formatTitle(title: String?): String? {
        return title
    }

    override fun getColor(status: ProgressStatus): String? {
        return when (status) {
            ProgressStatus.IN_PROGRESS -> primaryColor
            ProgressStatus.DONE -> ANSI_GREEN
            ProgressStatus.FAILED -> ANSI_RED
        }
    }

    override fun getDoneBlock(): String {
        return "="
    }

    override fun getArrowBlock(): String {
        return ">"
    }

    override fun getRemainingBlock(): String {
        return " "
    }

    override fun getBorders(): Pair<String, String>? {
        return Pair("[", "]")
    }

}