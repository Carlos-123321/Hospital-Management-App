package com.codelife.hospital

import java.time.Duration
import java.time.LocalDateTime

data class Appointment(
    val id: Int,
    val doctorId: Int?,
    val patientId: Int?,
    var date: LocalDateTime?,
    var timeSlot: Duration?,
    var status: String? // e.g., "Scheduled", "Completed", "Cancelled"
)

