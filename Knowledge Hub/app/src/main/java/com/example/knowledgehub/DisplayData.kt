package com.example.knowledgehub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity





class DisplayData : AppCompatActivity() {

    private var appcontent  = mapOf(
        "Android development" to "Android development is the process of creating applications for devices running the Android operating system, which is the most widely used mobile platform globally. It offers developers a flexible environment to build applications for smartphones, tablets, wearables, and other connected devices.\n" +
                "\n" +
                "The primary language used in Android development is Java, but in recent years, Kotlin has become the preferred choice due to its concise syntax, enhanced safety features, and full interoperability with Java. Kotlin is now officially supported by Google for Android development, making it a more modern and efficient option for new projects.\n" +
                "\n" +
                "To get started with Android development, you’ll need to set up Android Studio, the official Integrated Development Environment (IDE) provided by Google. It comes with powerful tools for coding, testing, and debugging Android apps, and it supports both Java and Kotlin. Once you’ve installed Android Studio, you can begin by creating a new project, selecting the type of app you want to build, and designing the user interface (UI) using XML.\n" +
                "\n" +
                "The Android SDK (Software Development Kit) provides all the necessary libraries, APIs, and tools to interact with the device hardware and software. A basic understanding of UI design, such as how to use Views and Layouts, as well as knowledge of app architecture like MVVM or MVC, is essential.\n" +
                "\n" +
                "Learning Android development typically involves studying programming fundamentals, working through tutorials, and building small projects to grasp concepts like data persistence, network communication, and handling user input.",
        "Cyber Security" to "Cybersecurity development focuses on protecting systems, networks, and programs from digital attacks, ensuring confidentiality, integrity, and availability of data. It involves creating solutions to prevent unauthorized access, data breaches, and cyber threats. Key programming languages in cybersecurity include Python, C, C++, and Java, as they are widely used for developing security tools, exploits, and analyzing vulnerabilities.\n" +
                "\n" +
                "To get started in cybersecurity, it is important to learn about network protocols, encryption, authentication, and malware analysis. You should familiarize yourself with tools like Wireshark, Metasploit, and Nmap. Knowledge of Linux systems is essential, as many security tools and exploits are built for it. To build expertise, consider pursuing certifications like Certified Ethical Hacker (CEH) or CompTIA Security+. Hands-on practice through Capture The Flag (CTF) challenges and setting up your own lab for penetration testing will also help solidify your skills.",
        "Web Development" to "Web development is the process of building websites and web applications for the internet. It involves both the creation of the structure of the site (called the front-end) and its functionality (called the back-end).\n" +
                "\n" +
                "Front-end development is what users see when they visit a website. It includes the design, layout, and interactive features. Front-end developers typically use languages like HTML (Hypertext Markup Language) for structure, CSS (Cascading Style Sheets) for design and layout, and JavaScript to add interactivity (like pop-up windows, buttons, etc.).\n" +
                "\n" +
                "Back-end development is the part of web development that handles the server, database, and application logic. This is what makes the website function. Back-end developers work with languages like PHP, Python, Ruby, or Node.js to create the server-side code that processes requests, interacts with databases, and delivers the right content to users.\n" +
                "\n" +
                "To make a website accessible, developers also work with databases (like MySQL or MongoDB) to store data such as user accounts or product information. Additionally, web frameworks like Django (Python) or Laravel (PHP) help developers speed up the process of writing code by providing templates and tools.\n" +
                "\n" +
                "Web development also includes tasks like responsive design (ensuring websites look good on all devices), security (protecting data and users), and search engine optimization (SEO) to make websites more discoverable by search engines like Google.","iOS development" to "iOS development focuses on creating applications for Apple’s mobile devices, including iPhones and iPads. The primary programming languages for iOS development are Swift and Objective-C, with Swift being the modern and preferred choice due to its simplicity and performance.\n" +
                "\n" +
                "To begin iOS development, you need a Mac with the Xcode IDE, where you can write code, design interfaces with SwiftUI, and test your apps. Key frameworks for iOS include UIKit and Core Data. Understanding Model-View-Controller (MVC) or Model-View-ViewModel (MVVM) architecture is important for structuring your applications.\n" +
                "\n" +
                "To gain expertise, consider following Apple's official documentation, building simple apps, and publishing them on the App Store. Swift Playground is a great way to start practicing. Certifications such as the Apple Developer Program also enhance credibility in iOS development.",
        "Blockchain Development" to "Blockchain development involves creating decentralized applications (DApps) using blockchain technology, which ensures data is distributed, transparent, and secure. Popular blockchain platforms include Ethereum, Solana, and Binance Smart Chain. The primary programming languages used are Solidity for writing smart contracts on Ethereum and Rust or Go for other blockchains.\n" +
                "\n" +
                "To start blockchain development, learn about distributed ledgers, consensus mechanisms (e.g., Proof of Work), and how blockchain ensures transparency and security. You’ll also need a solid understanding of cryptography. The best way to begin is by creating simple smart contracts, testing them on platforms like Remix or Truffle Suite, and experimenting with DApps.\n" +
                "\n" +
                "As you gain experience, you can explore developing for specific use cases like decentralized finance (DeFi), NFTs, or supply chain management. Participating in blockchain development communities and contributing to open-source projects are excellent ways to build expertise and stay updated with the rapidly evolving field."
        ,
        "Machine Learning" to "Machine learning (ML) is a subset of artificial intelligence (AI) that focuses on developing algorithms and models that allow computers to learn from and make predictions based on data. It involves statistical methods and the development of systems that improve their performance over time with experience.\n" +
                "\n" +
                "The most common languages used for machine learning are Python and R, with Python being the dominant choice due to its simplicity and powerful libraries like TensorFlow, Keras, Scikit-learn, and PyTorch. These libraries provide pre-built functions for model training, data manipulation, and evaluation.\n" +
                "\n" +
                "To start learning machine learning, it’s important to have a foundation in linear algebra, statistics, and calculus, as these mathematical concepts are the basis for many machine learning algorithms. You should first learn data handling using libraries like Pandas and NumPy, and understand the basic types of machine learning: supervised learning, unsupervised learning, and reinforcement learning.\n" +
                "\n" +
                "The next step is to implement basic algorithms such as linear regression, decision trees, SVM, and k-means clustering. Afterward, you can delve into more advanced techniques such as deep learning and neural networks.\n" +
                "\n" +
                "To gain expertise, practice through online courses and projects, participate in platforms like Kaggle for hands-on challenges, and study research papers to stay updated. Implementing machine learning models on real-world datasets and improving them iteratively is crucial for building your skills."
    )


    private fun setData(heading: String, body: String?) {
        findViewById<TextView>(R.id.heading).text=heading
        findViewById<TextView>(R.id.bodyContent).text=body

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_display_data)
        val i:Intent= intent

        val id: Int = i.getIntExtra("id",0)
        Log.d("id After", "$id ${R.id.Android}")

        when(id){
            R.id.Android->{setData("Android development",appcontent["Android development"])}
            R.id.CyberSecurity->{setData("Cyber Security",appcontent["Cyber Security"])}
            R.id.Web->{setData("Web Development",appcontent["Web Development"])}
            R.id.Ios->{setData("iOS development",appcontent["iOS development"])}
            R.id.BlockChain->{setData("Blockchain Development",appcontent["Blockchain Development"])}
            R.id.Ml->{setData("Machine Learning",appcontent["Machine Learning"])}
        }

    }
}