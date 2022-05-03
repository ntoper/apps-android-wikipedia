package org.wikipedia.analytics.eventplatform

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.wikipedia.auth.AccountUtil

class EditHistoryInteractionEvent(private var wikiDb: String, private var pageId: Int) :
    TimedEvent() {

    fun logShowHistory() { submitEvent("show_history") }
    fun logSearchClick() { submitEvent("search_click") }
    fun logFilterClick() { submitEvent("filter_click") }
    fun logFilterSelect() { submitEvent("filter_select") }

    fun logRevisionView() { submitEvent("revision_view") }
    fun logRevisionGoNewer() { submitEvent("revision_go_newer") }
    fun logRevisionGoOlder() { submitEvent("revision_go_older") }
    fun logShare() { submitEvent("revision_share") }
    fun logWatchToggle() { submitEvent("watch_toggle") }

    fun logCompareStart() { submitEvent("compare_start") }
    fun logCompareConfirm() { submitEvent("compare_confirm") }

    fun logThankClick() { submitEvent("thank_click") }
    fun logThankSuccess() { submitEvent("thank_success") }
    fun logThankCancel() { submitEvent("thank_cancel") }
    fun logThankError() { submitEvent("thank_error") }

    fun logUndoClick() { submitEvent("undo_click") }
    fun logUndoSuccess() { submitEvent("undo_success") }
    fun logUndoCancel() { submitEvent("undo_cancel") }
    fun logUndoError() { submitEvent("undo_error") }

    private fun submitEvent(action: String) {
        EventPlatformClient.submit(EditHistoryInteractionEventImpl(!AccountUtil.isLoggedIn, duration, wikiDb, pageId, action))
    }

    @Suppress("unused")
    @Serializable
    @SerialName("/analytics/mobile_apps/android_edit_history_interaction/1.0.0")
    class EditHistoryInteractionEventImpl(@SerialName("is_anon") private val isAnon: Boolean,
                                      @SerialName("time_spent_ms") private var timeSpentMs: Int,
                                      @SerialName("wiki_db") private var wikiDb: String,
                                      @SerialName("page_id") private var pageId: Int,
                                      private val action: String) :
        MobileAppsEvent("android.edit_history_interaction")
}
