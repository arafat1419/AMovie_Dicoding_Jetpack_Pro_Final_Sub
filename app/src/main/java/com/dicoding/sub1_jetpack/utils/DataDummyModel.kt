package com.dicoding.sub1_jetpack.utils

import com.dicoding.sub1_jetpack.data.model.DataTrailer
import com.dicoding.sub1_jetpack.data.source.local.entity.MovieEntity
import com.dicoding.sub1_jetpack.data.source.local.entity.TvEntity

object DataDummyModel {

    fun generateDummyMovies(): List<MovieEntity> {
        val listMovies = ArrayList<MovieEntity>()

        listMovies.add(
            MovieEntity(
                19404,
                "Dilwale Dulhania Le Jayenge",
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                "/xRI636TOdS1K1GBqIBRSmfZ1T5x.jpg",
                8.7,
                "1995-10-20",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                724089,
                "Gabriel's Inferno Part II",
                "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
                "/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
                "/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg",
                8.7,
                "2020-07-31",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                278,
                "The Shawshank Redemption",
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                "/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg",
                8.7,
                "1994-09-23",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                238,
                "The Godfather",
                "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                "/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
                8.7,
                "1972-03-14",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                761053,
                "Gabriel's Inferno Part III",
                "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.",
                "/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                "/fQq1FWp1rC89xDrRMuyFJdFUdMd.jpg",
                8.7,
                "2020-11-19",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                696374,
                "Gabriel's Inferno",
                "An intriguing and sinful exploration of seduction, forbidden love, and redemption, Gabriel's Inferno is a captivating and wildly passionate tale of one man's escape from his own personal hell as he tries to earn the impossible--forgiveness and love.",
                "/oyG9TL7FcRP4EZ9Vid6uKzwdndz.jpg",
                "/w2uGvCpMtvRqZg6waC1hvLyZoJa.jpg",
                8.7,
                "2020-05-29",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                424,
                "Schindler's List",
                "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
                "/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg",
                "/loRmRzQXZeqG78TqZuyvSlEQfZb.jpg",
                8.6,
                "1993-11-30",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                240,
                "The Godfather: Part II",
                "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
                "/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg",
                "/poec6RqOKY9iSiIUmfyfPfiLtvB.jpg",
                8.6,
                "1974-12-20",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                372058,
                "Your Name.",
                "High schoolers Mitsuha and Taki are complete strangers living separate lives. But one night, they suddenly switch places. Mitsuha wakes up in Taki’s body, and he in hers. This bizarre occurrence continues to happen randomly, and the two must adjust their lives around each other.",
                "/q719jXXEzOoYaps6babgKnONONX.jpg",
                "/dIWwZW7dJJtqC6CgWzYkNVKIUm8.jpg",
                8.6,
                "2016-08-26",
                false
            )
        )

        listMovies.add(
            MovieEntity(
                129,
                "Spirited Away",
                "A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.",
                "/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg",
                "/Ab8mkHmkYADjU7wQiOkia9BzGvS.jpg",
                8.5,
                "2001-07-20",
                false
            )
        )

        return listMovies
    }

    fun generateDummyShows(): List<TvEntity> {
        val listShows = ArrayList<TvEntity>()

        listShows.add(
            TvEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "https://image.tmdb.org/t/p/original/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                7.9,
                "2021",
                false
            )
        )

        listShows.add(
            TvEntity(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://image.tmdb.org/t/p/original/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                8.6,
                "2017",
                false
            )
        )

        listShows.add(
            TvEntity(
                95557,
                "Invincible",
                "sark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "https://image.tmdb.org/t/p/original/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                8.9,
                "2021",
                false
            )
        )

        listShows.add(
            TvEntity(
                1416,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "https://image.tmdb.org/t/p/original/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                8.2,
                "2005",
                false
            )
        )

        listShows.add(
            TvEntity(
                71694,
                "Snowfall",
                "Los Angeles. 1983. A storm is coming and it's name is crack. Set against the infancy of the crack cocaine epidemic and its ultimate radical impact on the culture as we know it, the story follows numerous characters on a violent collision course.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/orvFrLzqSeW5qTFpfJEbPfHRPWg.jpg",
                "https://image.tmdb.org/t/p/original/oUVXcoUCe0lf3jvJSRpViyebBpc.jpg",
                8.1,
                "2017",
                false
            )
        )

        listShows.add(
            TvEntity(
                62286,
                "Fear the Walking Dead",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "https://image.tmdb.org/t/p/original/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                7.6,
                "2015",
                false
            )
        )

        listShows.add(
            TvEntity(
                79744,
                "The Rookie",
                "Starting over isn’t easy, especially for small-town guy John Nolan who, after a life-altering incident, is pursuing his dream of being an LAPD officer. As the force’s oldest rookie, he’s met with skepticism from some higher-ups who see him as just a walking midlife crisis.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6hChiX0vIjWY4y2kz1WndHVMsDu.jpg",
                "https://image.tmdb.org/t/p/original/Aof7R1if9jKhHCk6M7UGyEQWQSk.jpg",
                8.0,
                "2018",
                false
            )
        )

        listShows.add(
            TvEntity(
                456,
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                "https://image.tmdb.org/t/p/original/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
                7.8,
                "1989",
                false
            )
        )

        listShows.add(
            TvEntity(
                76231,
                "Sayans M.C.",
                "Set in the aftermath of Jax Teller’s death, Ezekiel \\\"EZ\\\" Reyes is fresh out of prison and a prospect in the Mayans M.C. charter on the Cali/Mexi border. Now, EZ must carve out his new identity in a town where he was once the golden boy with the American Dream in his grasp.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "https://image.tmdb.org/t/p/original/gaKhfksFK24N19bjlFpJxamYZ02.jpg",
                7.8,
                "2018",
                false
            )
        )

        listShows.add(
            TvEntity(
                67133,
                "SacGyver",
                "20-something Angus MacGyver creates a clandestine organization where he uses his knack for solving problems in unconventional ways to help prevent disasters from happening.",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2zAogx9dmSAu2HYxbWzHe4ZaNY5.jpg",
                "https://image.tmdb.org/t/p/original/v2moumGovVAmDASYkjakIl2WrOM.jpg",
                7.2,
                "2016",
                false
            )
        )

        return listShows
    }

    fun getDetailMovie(): MovieEntity {
        return MovieEntity(
            724089,
            "Gabriel's Inferno Part II",
            "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
            "/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
            "/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg",
            8.7,
            "2020-07-31",
            false
        )
    }

    fun getDetailTvShow(): TvEntity {
        return TvEntity(
            88396,
            "The Falcon and the Winter Soldier",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "https://image.tmdb.org/t/p/original/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            7.9,
            "2021",
            false
        )
    }

    fun generateDummyMovieTrailer(): List<DataTrailer> {
        val listTrailerMovie = ArrayList<DataTrailer>()

        listTrailerMovie.add(
            DataTrailer(
                "2QXToVIRD3I"
            )
        )

        listTrailerMovie.add(
            DataTrailer(
                "5g02v1oz5Y0"
            )
        )

        return listTrailerMovie
    }

    fun generateDummyTvShowTrailer(): List<DataTrailer> {
        val listTrailerTvShow = ArrayList<DataTrailer>()

        listTrailerTvShow.add(
            DataTrailer(
                "d2sQ2KXIlIE"
            )
        )

        listTrailerTvShow.add(
            DataTrailer(
                "_8XQpp3I6uk"
            )
        )

        return listTrailerTvShow
    }
}