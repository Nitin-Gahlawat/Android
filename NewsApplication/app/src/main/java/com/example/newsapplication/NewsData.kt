package com.example.newsapplication

data class News(val heading:String,val body:String,val img:Int)

class NewsData {
    companion object {
        private val heading = arrayOf(
            "U.K. Foreign Secretary",
            "Cooking gas",
            "Joe Biden",
            "Russian Foreign Ministry",
            "China visits",
            "China and us",
            "Largest gathering of Foreign Ministers"
        )
        private val newsbody = arrayOf(
            "U.K. Foreign Secretary James Cleverly raises issue of BBC tax searches with EAM Jai shankar",
            "Cooking gas prices hiked by ₹50 for domestic, ₹350.50 for commercial cylinders",
            "Joe Biden appoints two prominent Indian-American corporate leaders to his Export Council",
            "Sergey Lavrov will raise suspected bombing of Nord Stream II at G20: Russian Foreign Ministry",
            "Belarusian leader Lukashenko visits China amid Ukraine tensions",
            "China rips new U.S. House committee on countering Beijing",
            "Largest gathering of Foreign Ministers hosted by any G20 presidency: Foreign Secretary Vinay Kwatra"
        )
        private val newsImageArray = arrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img1
        )


        private var data: ArrayList<News> = arrayListOf()

        init{
            for (i in heading.indices) {
                data.add(News(heading[i], newsbody[i], newsImageArray[i]))
            }
        }

        fun getData():ArrayList<News>{
            return data
        }

    }
}