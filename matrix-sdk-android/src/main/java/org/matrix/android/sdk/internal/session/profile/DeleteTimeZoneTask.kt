package org.matrix.android.sdk.internal.session.profile

import org.matrix.android.sdk.internal.network.GlobalErrorReceiver
import org.matrix.android.sdk.internal.network.executeRequest
import org.matrix.android.sdk.internal.task.Task
import javax.inject.Inject

internal abstract class DeleteTimeZoneTask : Task<DeleteTimeZoneTask.Params, Unit> {
    data class Params(
            val userId: String
    )
}

internal class DefaultDeleteTimeZoneTask @Inject constructor(
        private val profileAPI: ProfileAPI,
        private val globalErrorReceiver: GlobalErrorReceiver
) : DeleteTimeZoneTask() {

    override suspend fun execute(params: Params) {
        val body = SetTimeZoneBody(
                m_tz  = null
        )
        return executeRequest(globalErrorReceiver) {
            profileAPI.setTimeZone(params.userId, body)
        }
    }
}
