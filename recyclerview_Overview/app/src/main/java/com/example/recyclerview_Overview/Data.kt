package com.example.recyclerview_Overview

data class ExamClass(var name: String, var date: String, var topic: String)

var ExamList: ArrayList<ExamClass> = arrayListOf(
    ExamClass("Physics", "10-10-2024", "Write about reflection"),
    ExamClass("Chemistry", "1-1-2025", "Carbon bonding"),
    ExamClass("Web Development", "14-1-2025", "For loop in JS"),
    ExamClass("Maths", "15-1-2025", "Trigonometry"),
    ExamClass("Android Development", "16-1-2025", "Function in Kotlin"),
    ExamClass("AI and ML", "18-1-2024", "Intro to Python"),
    ExamClass("Biology", "20-1-2025", "Human cell structure"),
    ExamClass("History", "22-1-2025", "World War II"),
    ExamClass("Geography", "25-1-2025", "Map reading skills"),
    ExamClass("Economics", "28-1-2025", "Supply and Demand"),
    ExamClass("Literature", "30-1-2025", "Shakespearean plays"),
    ExamClass("Philosophy", "2-2-2025", "Ethical theories"),
    ExamClass("Computer Science", "5-2-2025", "Data structures"),
    ExamClass("Sociology", "7-2-2025", "Social institutions"),
    ExamClass("Psychology", "10-2-2025", "Behavioral theories")
)
