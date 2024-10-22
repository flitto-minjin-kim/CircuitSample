package com.minjin.sample.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.minjin.sample.model.Email

@Composable
fun EmailItem(
    email: Email,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Magenta)
                .padding(4.dp),
            imageVector = Icons.Default.Person,
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = null,
        )

        Column {
            Row {
                Text(
                    text = email.sender,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = email.timestamp,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.alpha(0.5f),
                )
            }

            Text(
                text = email.subject,
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = email.body,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(0.5f),
            )
        }
    }
}

@Composable
fun EmailDetailContent(
    modifier: Modifier = Modifier,
    email: Email,
) {
    Column(modifier.padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Magenta)
                    .padding(4.dp),
                imageVector = Icons.Default.Person,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = null,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row {
                    Text(
                        text = email.sender,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = email.timestamp,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.alpha(0.5f),
                    )
                }

                Text(
                    text = email.subject,
                    style = MaterialTheme.typography.labelMedium
                )

                Row {
                    Text(
                        text = "To: ",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = email.recipients.joinToString(","),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.alpha(0.5f),
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            text = email.body,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
