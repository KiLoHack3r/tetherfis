/*
 * Copyright 2024 pyamsoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pyamsoft.tetherfi.status.trouble

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.pyamsoft.pydroid.theme.keylines
import com.pyamsoft.pydroid.ui.app.rememberDialogProperties
import com.pyamsoft.pydroid.ui.defaults.DialogDefaults
import com.pyamsoft.pydroid.ui.haptics.LocalHapticManager

@Composable
internal fun TroubleshootDialog(
    modifier: Modifier = Modifier,
    appName: String,
    isBroadcastError: Boolean,
    isProxyError: Boolean,
    onDismiss: () -> Unit,
) {
  val hapticManager = LocalHapticManager.current

  Dialog(
      properties = rememberDialogProperties(),
      onDismissRequest = onDismiss,
  ) {
    Box(
        modifier =
            modifier
                .padding(horizontal = MaterialTheme.keylines.content)
                .padding(bottom = MaterialTheme.keylines.content),
    ) {
      Surface(
          modifier = Modifier.fillMaxWidth(),
          shadowElevation = DialogDefaults.Elevation,
          shape = MaterialTheme.shapes.medium,
      ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.keylines.content),
        ) {
          TroubleshootUnableToStart(
              modifier = Modifier.fillMaxWidth(),
              appName = appName,
              isBroadcastError = isBroadcastError,
              isProxyError = isProxyError,
          )
          Row(
              verticalAlignment = Alignment.CenterVertically,
          ) {
            Spacer(
                modifier = Modifier.weight(1F),
            )

            TextButton(
                onClick = {
                  hapticManager?.cancelButtonPress()
                  onDismiss()
                },
            ) {
              Text(
                  text = "Close",
              )
            }
          }
        }
      }
    }
  }
}
