package de.sldk.kotbar

interface ProgressBar {

    fun formatTitle(title: String?): String?
    fun getColor(status: ProgressStatus): String?
    fun getDoneBlock(): String
    fun getArrowBlock(): String
    fun getRemainingBlock(): String
    fun getBorders(): Pair<String, String>?
    fun getInfoBlock(percentage: Int, progress: Long, size: Long, status: ProgressStatus): String

}