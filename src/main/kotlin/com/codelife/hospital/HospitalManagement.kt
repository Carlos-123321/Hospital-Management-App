package com.codelife.hospital
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.Duration
import java.time.LocalDate

//Lists have to be mutable in order to add objects to it
val doctorsList: MutableList<Doctor> = mutableListOf(
    Doctor(1, "Smith", "Cardiologist"),
    Doctor(2, "Joe", "Pediatrician"),
    Doctor(3, "Anderson", "Orthopedic Surgeon"),
    Doctor(4, "Williams", "Dermatologist")
)

val patientsList: MutableList<Patient> = mutableListOf(
    Patient(1, "Carlos", 19),
    Patient(2, "Gabriel", 20),
    Patient(3, "Sophia", 25),
    Patient(4, "Ethan", 30)
)

val appointmentsList: MutableList<Appointment> = mutableListOf(
    Appointment(1, 1, 1, LocalDateTime.now(), Duration.ZERO, "Scheduled"),
    Appointment(2, 2, 2, LocalDateTime.now().plusDays(1), Duration.ZERO, "Scheduled"),
    Appointment(3, 3, 3, LocalDateTime.now().plusDays(3), Duration.ZERO, "Scheduled")
)

fun main() {

    println("Welcome to CodeLife General Hospital Management System!")
    println("Select the operation you would like to do! \n")

    val boolean = true;
    while (boolean){

        println(
                "To register a doctor please press the number one : 1\n" +
                "To register a patient please press the number two : 2\n"+
                "To schedule an appointment please press the number three : 3\n" +
                "To reschedule an appointment please press the number four : 4\n" +
                "To cancel an appointment please press the number five : 5\n" +
                "To display a doctor's schedule please press the number six : 6\n")

        val numberInput = readLine()

        val numberChosen = when (numberInput){
            "1" -> registerDoctor()
            "2" -> registerPatient()
            //Passing the appointmentsList, doctorsList, patientsList as arguments
            //to the scheduleAppointment function, so we can access their properties
            "3" -> scheduleAppointment(appointmentsList, doctorsList, patientsList)
            "4" -> rescheduleAppointment(appointmentsList)
            "5" -> cancelAppointment(appointmentsList)
            "6" -> showDoctorSchedule(appointmentsList)

            else -> println("Please select a valid operation!")
        }
    }


}

fun registerDoctor() {
    val id = InputUtils.readInt("Enter Doctor ID:", "Invalid ID. Please enter a valid integer.")
    val name = InputUtils.readString("Enter Doctor's Name:", "Name cannot be empty.")
    val specialty = InputUtils.readString("Enter Doctor's Specialty:", "Specialty cannot be empty.")
    //Creating a newDoctor object which stores the id, name and specialty entered by the user
    val newDoctor = Doctor(id, name, specialty)

    //Adding the newDoctor object to the doctorsList list
    doctorsList += newDoctor
    println("Doctor registered: $newDoctor")
    println(doctorsList)
}

fun registerPatient() {
    val id = InputUtils.readInt("Enter Patient ID:", "Invalid ID. Please enter a valid integer.")
    val name = InputUtils.readString("Enter Patient's Name:", "Name cannot be empty.")
    val age = InputUtils.readInt("Enter Patient's Age:", "Please enter a valid integer for age.")
    //Creating a newPatient object which stores the id, name and age entered by the user
    val newPatient = Patient(id, name, age)

    //Adding the newPatient object to the patientsList list
    patientsList += newPatient
    println("Patient registered: $newPatient")
    println(patientsList)
}

    fun scheduleAppointment(
        //Passing each of the lists as arguments, so we can access their properties
        appointmentsList: MutableList<Appointment>,
        doctorsList: MutableList<Doctor>,
        patientsList: MutableList<Patient>
    ) {

        println()
        println("All Appointment ID's:")
        //Looping through each appointment in the appointmentsList and displaying appointment ids
        for (appointment in appointmentsList) {
            println("Appointment ID: ${appointment.id}")
        }
        println()

        println("Please enter an id for your appointment")

        //Why we need this might be confusing, but we need
        //appointmentId to be initialized to 0 in order to have the ability to
        //assign it the value of userAppointmentId which is an int later on...
        //Also, appointmentId is declared outside the while loop which means we
        //are going to be able to use it outside the while loop's scope
        var appointmentId: Int = 0
        var isUniqueAID = true

        while(isUniqueAID) {

            val userAppointmentId = InputUtils.readInt("Enter Appointment ID:", "Please enter a valid appointment id!")

            // Creating a boolean variable existingAppointmentId
            //if the appointmentId that the user entered is found within the appointment ids
            //of the appointmentLists list the returned value shall be true
            val existingAppointmentId = appointmentsList.any { it.id == userAppointmentId }

            //if the entered appointment id is found
            if (existingAppointmentId) {
                println("The entered appointment ID already exists.")
                println("Please enter an appointment ID that doesn't already exist!")

            }
            //if the entered appointment id is not found
            else {
                println("The entered appointment ID is unique")
                //Since the entered appointment ID is unique, assign
                //this int value to the appointmentID variable located outside this while loop
                appointmentId = userAppointmentId
                isUniqueAID = false
            }
        }

        println()
        println("All Doctor ID's:")
        for (doctor in doctorsList) {
            println("Doctor ID: ${doctor.id}")
        }

        println()
        println("Please enter the id of your chosen doctor")

        //Same thing here as previously explained
        var doctorId: Int = 0
        var isUniqueDID = true

        while(isUniqueDID) {

            val userDoctorId = InputUtils.readInt("Enter Doctor ID:", "Please enter a valid doctor id!")
            println()

            val existingDoctorId = appointmentsList.any { it.id == userDoctorId }

            //If the doctor id that the user entered doesn't match an id already existing in the doctorsList
            if (!existingDoctorId) {
                println("Please enter a doctor ID that exists!")

            }
            //If the doctor id that the user entered matches an id already existing in the doctorsList
            else {
                println("The entered doctor ID is recognized.")
                doctorId = userDoctorId
                println()
                isUniqueDID = false

            }
        }

        println("All Patient ID's:")
        for (patient in patientsList) {
            println("Patient ID: ${patient.id}")
        }

        println()
        println("Please enter your patient Id")

        //Same thing here as previously explained
        var patientId: Int = 0
        var isUniquePID = true

        while(isUniquePID) {

            val userPatientId = InputUtils.readInt("Enter Patient ID:", "Please enter a valid patientId!")
            println()

            val existingPatientId = appointmentsList.any { it.id == userPatientId }

            //If the patient id that the user entered doesn't match an id already existing in the patientsList
            if (!existingPatientId) {
                println("Please enter a patient ID that exists!")

            }
            //If the patient id that the user entered matches an id already existing in the patientsList
            else {
                println("The entered patient ID is recognized.")
                patientId = userPatientId
                println()
                isUniquePID = false

            }
        }


        println()

        //Creating the dateFormat we want our users to use
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

        println("Please enter the date and time of the appointment you want to make!")
        println("Enter the date and time in the following format: dd/MM/yyyy HH:mm:ss")
        println("For example: 15/01/2024 14:30:45")

        //Storing the user's input as a String? no matter if the user entered
        var dateInput = readLine()
        var validDate = false

        //Creating an appointmentDateTime variable to store a valid date entered by the user
        //It's value it's null for the moment because we don't know if the user entered
        var appointmentDateTime: LocalDateTime? = null

        while (!validDate) {
            try {
                appointmentDateTime = LocalDateTime.parse(dateInput, dateFormat)
                println("You have entered: $appointmentDateTime")
                println()
                validDate = true
            } catch (e: Exception) {
                println("Invalid date and time format. Please make sure to use the correct format.")
                println()
                println("Enter the date and time in the following format: dd/MM/yyyy HH:mm:ss")
                println("For example: 15/01/2024 14:30:45")
                dateInput = readlnOrNull()
            }
        }

        println("Please enter the duration of your appointment!")
        println("Enter the duration of your appointment in the following format: HH:mm:ss")
        println("For example: 14:30:45")

        var durationInput = readLine()

        var validDuration = false

        var appointmentDurationTime: Duration? = null

        while (!validDuration) {
            try {
                    val parts = durationInput!!.split(":")
                    val hours = parts[0].toLong()
                    val minutes = parts[1].toLong()
                    val seconds = parts[2].toLong()

                    appointmentDurationTime = Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds)

                    println("You have entered: $appointmentDurationTime")
                    println()

                val hasConflict = appointmentsList.any { existingAppointment ->
                    existingAppointment.doctorId == doctorId &&
                            doAppointmentsOverlap(
                                existingAppointment.date,
                                existingAppointment.timeSlot,
                                appointmentDateTime,
                                appointmentDurationTime
                            )
                }

                if(hasConflict){
                    println("Sorry, the selected time slot is already occupied. Please choose another time.")
                    println()
                }
                else{
                    val newAppointment = Appointment(appointmentId, doctorId, patientId, appointmentDateTime, appointmentDurationTime, "Scheduled")
                    appointmentsList += newAppointment

                    println("All appointments:")
                    for (appointment in appointmentsList) {
                        println("$appointment")
                    }
                    println()
                }
                    validDuration = true

            } catch (e: Exception) {
                println("Invalid duration format. Please make sure to use the correct format.")
                println()
                println("Enter the duration of your appointment in the following format: HH:mm:ss")
                println("For example: 14:30:45")
                durationInput = readLine()
            }
        }
    }

    fun rescheduleAppointment(appointmentsList: MutableList<Appointment>) {

        var validAppointmentId = true

        while(validAppointmentId) {

        val userAppointmentId = InputUtils.readInt("Enter the ID of the appointment you want to reschedule: ",
            "Please enter an existing appointment ID:")

            val existingAppointment = appointmentsList.find { it.id == userAppointmentId }

            if (existingAppointment != null) {

                val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

                println("Please enter the new date and time for your re-scheduled appointment!")
                println("Enter the date and time in the following format: dd/MM/yyyy HH:mm:ss")
                println("For example: 15/01/2024 14:30:45")

                var dateInput = readLine()
                var validDate = false

                var appointmentDateTime: LocalDateTime? = null

                while (!validDate) {
                    try {
                        appointmentDateTime = LocalDateTime.parse(dateInput, dateFormat)
                        println("You have entered: $appointmentDateTime")
                        println()
                        validDate = true
                    } catch (e: Exception) {
                        println("Invalid date and time format. Please make sure to use the correct format.")
                        println()
                        println("Enter the date and time in the following format: dd/MM/yyyy HH:mm:ss")
                        println("For example: 15/01/2024 14:30:45")
                        dateInput = readlnOrNull()
                    }
                }

                println("Please enter the duration of your appointment!")
                println("Enter the duration of your appointment in the following format: HH:mm:ss")
                println("For example: 14:30:45")

                var durationInput = readLine()

                var validDuration = false

                var appointmentDurationTime: Duration? = null

                while (!validDuration) {
                    try {
                        val parts = durationInput!!.split(":")
                        val hours = parts[0].toLong()
                        val minutes = parts[1].toLong()
                        val seconds = parts[2].toLong()

                        appointmentDurationTime = Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds)

                        println("You have entered: $appointmentDurationTime")
                        println()

                        val hasConflict = appointmentsList.any { existingAppointment ->
                                    doAppointmentsOverlap(
                                        existingAppointment.date,
                                        existingAppointment.timeSlot,
                                        appointmentDateTime,
                                        appointmentDurationTime
                                    )
                        }

                        if(hasConflict){
                            println("Sorry, the selected time slot is already occupied. Please choose another time.")
                            println()
                        }
                        else{
                           existingAppointment.date = appointmentDateTime
                           existingAppointment.timeSlot = appointmentDurationTime

                           println("Appointment Successfully Re-Scheduled")

                            println("All appointments:")
                            for (appointment in appointmentsList) {
                                println("$appointment")
                            }
                            println()
                        }
                        validDuration = true

                    } catch (e: Exception) {
                        println("Invalid duration format. Please make sure to use the correct format.")
                        println()
                        println("Enter the duration of your appointment in the following format: HH:mm:ss")
                        println("For example: 14:30:45")
                        durationInput = readLine()
                    }
                }
                validAppointmentId = false
            }
        }
    }

    fun cancelAppointment(appointmentsList: MutableList<Appointment>) {

        println("Please enter the id of the appointment you would like to cancel")

        var boolean = false
        while (!boolean) {
            val userAppointmentId = InputUtils.readInt("Enter appointment ID:", "Please enter a valid appointment Id!")

            val removedAppointmentId = appointmentsList.find { it.id == userAppointmentId }

            if (removedAppointmentId != null) {
                removedAppointmentId.status = "Cancelled"
                appointmentsList.removeIf { it.id == userAppointmentId }
                println("Appointment canceled successfully:")
                println(removedAppointmentId)
                println()
                println("All Appointments:")
                for (appointment in appointmentsList) {
                    println("${appointment}")
                }
                println()
                boolean = true
            } else {
                println("Appointment with ID $userAppointmentId not found.")
            }
        }
    }

    fun showDoctorSchedule(appointmentsList: MutableList<Appointment>) {

        println("Please enter the id of the doctor to display his appointments")
        val doctorId = InputUtils.readInt("Enter Doctor ID:", "Please enter a valid doctor id!")

        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        println("Please enter the date to see the doctor's appointments on that day!")
        println("Enter the date and time in the following format: dd/MM/yyyy")
        println("For example: 15/01/2024")

        var dateInput = readLine()
        var validDate = false

        var date: LocalDateTime? = null

        while (!validDate) {
            try {
                val localDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                date = localDate.atStartOfDay()
                println("You have entered: $date")
                println()
                validDate = true
            } catch (e: Exception) {
                println("Invalid date and time format. Please make sure to use the correct format.")
                println()
                println("Enter the date and time in the following format: dd/MM/yyyy")
                println("For example: 15/01/2024 14:30:45")
                dateInput = readlnOrNull()
            }
        }

        val doctorAppointments = appointmentsList.filter {
            it.doctorId == doctorId && it.date?.toLocalDate() == date?.toLocalDate()
        }

        // Sort appointments by timeSlot
        val sortedAppointments = doctorAppointments.sortedBy { it.timeSlot }

        // Display the appointments
        if (sortedAppointments.isNotEmpty()) {
            println("Doctor Schedule for ${date?.toLocalDate()}:")
            println()
            for (appointment in sortedAppointments) {
                println("${appointment.date?.toLocalTime()} - ${appointment.timeSlot} | Status: ${appointment.status}")
            }
            println()
        } else {
            println("No appointments scheduled for the specified doctor and date.")
            println()
        }

    }

fun doAppointmentsOverlap(
    date1: LocalDateTime?,
    duration1: Duration?,
    date2: LocalDateTime?,
    duration2: Duration?
): Boolean {
    return date1?.plus(duration1 ?: Duration.ZERO)!!.isAfter(date2) &&
            date2?.plus(duration2 ?: Duration.ZERO)!!.isAfter(date1)
}


