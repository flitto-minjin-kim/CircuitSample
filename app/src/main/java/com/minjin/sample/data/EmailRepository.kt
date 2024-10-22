package com.minjin.sample.data

import com.minjin.sample.model.Email
import javax.inject.Inject

interface EmailRepository {

    fun getEmails(): List<Email>

    fun getEmail(id: String): Email
}

class EmailRepositoryImpl @Inject constructor() : EmailRepository {

    override fun getEmails(): List<Email> {
        return mockEmails
    }

    override fun getEmail(id: String): Email {
        return mockEmails.find { it.id == id }!!
    }

    companion object {
        val mockEmails =
            listOf(
                Email(
                    id = "1",
                    subject = "Meeting re-sched!",
                    body = "Hey, I'm going to be out of the office tomorrow. Can we reschedule our meeting for Thursday or next week?",
                    sender = "Ali Connors",
                    timestamp = "3:00 PM",
                    recipients = listOf("all@example.com"),
                ),
                Email(
                    id = "2",
                    subject = "[AD] You're Enrolled! These Are Your Next Steps",
                    body = "Thanks for enrolling in my Best Practice Guide to Android Architecture! Please follow these steps to activate your access to the course materials:",
                    sender = "Philipp",
                    timestamp = "6:00 PM",
                    recipients = listOf("mail@example.com"),
                ),
            )
    }
}