package com.example.jetpackprosubmission.util

import com.example.jetpackprosubmission.data.MovieEntity
import com.example.jetpackprosubmission.data.TvShowEntity
import java.util.*

object DataDummy {
    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "528085",
                "2067",
                "/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
                "A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.",
                "105",
                "2020-10-01",
                "5.6"
            )
        )
        movies.add(
            MovieEntity(
                "724989",
                "Hard Kill",
                "/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
                "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                "113",
                "2020-10-23",
                "3.7"
            )
        )
        movies.add(
            MovieEntity(
                "635302",
                "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "127",
                "2020-10-16",
                "7.5"
            )
        )
        movies.add(
            MovieEntity(
                "741067",
                "Welcome to Sudden Death",
                "/elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg",
                "Jesse Freeman is a former special forces officer and explosives expert now working a regular job as a security guard in a state-of-the-art basketball arena. Trouble erupts when a tech-savvy cadre of terrorists kidnap the team's owner and Jesse's daughter during opening night. Facing a ticking clock and impossible odds, it's up to Jesse to not only save them but also a full house of fans in this highly charged action thriller.",
                "114",
                "2020-09-29",
                "6.4"
            )
        )
        movies.add(
            MovieEntity(
                "497582",
                "Enola Holmes",
                "/riYInlsq2kf1AWoGm80JQW5dLKp.jpg",
                "While searching for her missing mother, intrepid teen Enola Holmes uses her sleuthing skills to outsmart big brother Sherlock and help a runaway lord.",
                "93",
                "2020-09-23",
                "7.6"
            )
        )
        movies.add(
            MovieEntity(
                "590223",
                "Love and Monsters",
                "/r4Lm1XKP0VsTgHX4LG4syAwYA2I.jpg",
                "Seven years after the Monsterpocalypse, Joel Dawson, along with the rest of humanity, has been living underground ever since giant creatures took control of the land. After reconnecting over radio with his high school girlfriend Aimee, who is now 80 miles away at a coastal colony, Joel begins to fall for her again. As Joel realizes that there’s nothing left for him underground, he decides against all logic to venture out to Aimee, despite all the dangerous monsters that stand in his way.",
                "123",
                "2020-10-16",
                "7.7"
            )
        )
        movies.add(
            MovieEntity(
                "520763",
                "A Quiet Place Part II",
                "/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
                "ollowing the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
                "102",
                "2021-04-21",
                "0"
            )
        )
        movies.add(
            MovieEntity(
                "337401",
                "Mulan",
                "/aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg",
                "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.",
                "115",
                "2020-09-04",
                "7.3"
            )
        )
        movies.add(
            MovieEntity(
                "624963",
                "A Babysitter's Guide to Monster Hunting",
                "/bkld8Me0WiLWipLORRNfF1yIPHu.jpg",
                "Recruited by a secret society of babysitters, a high schooler battles the Boogeyman and his monsters when they nab the boy she's watching on Halloween.",
                "94",
                "2020-10-14",
                "6.1"
            )
        )
        movies.add(
            MovieEntity(
                "539885",
                "Ava",
                "/qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg",
                "A black ops assassin is forced to fight for her own survival after a job goes dangerously wrong.",
                "96",
                "2020-07-02",
                "5.8"
            )
        )

        return movies
    }

    fun generateDummyTvShows(): ArrayList<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                "77169",
                "Cobra Kai",
                "/eTMMU2rKpZRbo9ERytyhwatwAjz.jpg",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "2018-05-10",
                "2018-06-14",
                "9.4",
                "3",
                "21"
            )
        )
        tvShow.add(
            TvShowEntity(
                "76479",
                "The Boys",
                "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "2019-07-25",
                "2020-10-09",
                "8.4",
                "16",
                "2"
            )
        )
        tvShow.add(
            TvShowEntity(
                "62286",
                "Fear the Walking Dead",
                "/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "2015-08-23",
                "2020-10-25",
                "7.3",
                "85",
                "6"
            )
        )
        tvShow.add(
            TvShowEntity(
                "63174",
                "Lucifer",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "2016-01-25",
                "2020-08-21",
                "8.5",
                "75",
                "5"
            )
        )
        tvShow.add(
            TvShowEntity(
                "94305",
                "The Walking Dead: World Beyond",
                "/z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
                "A heroic group of teens sheltered from the dangers of the post-apocalyptic world leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.",
                "2020-10-04",
                "2020-10-25",
                "8.5",
                "10",
                "1"
            )
        )
        tvShow.add(
            TvShowEntity(
                "48866",
                "The 100",
                "/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
                "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
                "2014-03-19",
                "2020-09-30",
                "7.7",
                "100",
                "7"
            )
        )
        tvShow.add(
            TvShowEntity(
                "69050",
                "Riverdale",
                "/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                "2020-05-06",
                "8.6",
                "76",
                "4"
            )
        )
        tvShow.add(
            TvShowEntity(
                "1416",
                "Grey's Anatomy",
                "/jnsvc7gCKocXnrTXF6p03cICTWb.jpg",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005-03-27",
                "2020-04-09",
                "8",
                "363",
                "17"
            )
        )
        tvShow.add(
            TvShowEntity(
                "456",
                "The Simpsons",
                "/qcr9bBY6MVeLzriKCmJOv1562uY.jpg",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989-12-16",
                "2020-10-11",
                "7.6",
                "690",
                "32"
            )
        )
        tvShow.add(
            TvShowEntity(
                "1622",
                "Supernatural",
                "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "2005-09-13",
                "2020-10-22",
                "8.1",
                "327",
                "15"
            )
        )

        return tvShow
    }
}