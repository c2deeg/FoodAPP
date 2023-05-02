package com.app.marier.Handler

import com.app.marier.Models.EditUserDetail.EditUserDetailExample
import com.app.marier.Models.GetCurrentUser.GetCurrentUserExample

interface UpdateUserDetailHandler {
    fun onSuccess(editUserDetailExample: EditUserDetailExample?)
    fun onError(message: String?)
}