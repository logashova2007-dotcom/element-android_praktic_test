package org.matrix.android.sdk.internal.session.profile

import org.matrix.android.sdk.internal.network.GlobalErrorReceiver
import org.matrix.android.sdk.internal.network.executeRequest
import org.matrix.android.sdk.internal.session.presence.model.SetTimeZoneBody
import org.matrix.android.sdk.internal.task.Task
import javax.inject.Inject

internal abstract class SetTimeZoneTask : Task<SetTimeZoneTask.Params, Unit> {
    data class Params(
            val userId: String,
            val newTimeZoneUTC: String
    )
}

internal class DefaultSetTimeZoneTask @Inject constructor(
        private val profileAPI: ProfileAPI,
        private val globalErrorReceiver: GlobalErrorReceiver
) : SetTimeZoneTask() {

    override suspend fun execute(params: Params) {
        val body = SetTimeZoneBody(
                UTC = params.newTimeZoneUTC
        )
        return executeRequest(globalErrorReceiver) {
            profileAPI.setTimeZone(params.userId, body)
        }
    }
}
