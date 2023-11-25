import React, { useState, useEffect} from 'react';
import styles from "./Mypage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";

export default function Mypage() {
    const history = useNavigate();


    const[name, setName] = useState();
    const[selfIntro, setSelfIntro] = useState();
    const[bojRank, setBojRank] = useState();
    const[dguEmail, setDguEmail] = useState();
    const[userRank, setUserRank] = useState();
    const[teamExpCount, setTeamExpCount] = useState();
    const[leaderCount, setLeaderCount] = useState();
    const[devTestScore, setDevTestScore] = useState();
    const[userLanguageDTOS , setUserLanguageDTOS] =useState([]);
    const[userInterestFieldDTOS, setUserInterestFieldDTOS] = useState([]);
    const[userPersonalityDTOS, setUserPersonalityDTOS] =useState([]);
    const[userStudyFieldDTOS, setUserStudyFieldDTOS] = useState([]);

    let token = localStorage.getItem('token') || '';

    useEffect(() => {
        const handleClick = () => {
            try {
                fetch('http://localhost:8080/mypage', {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    }).then(res=>res.json())        
                        .then(res=> {
                        console.log(res)
                        setName(res.githubName);
                        setSelfIntro(res.selfIntro);
                        setBojRank(res.bojRank);
                        setDguEmail(res.dguEmail);
                        setUserRank(res.userRank);
                        setTeamExpCount(res.teamExpCount);
                        setLeaderCount(res.leaderCount);
                        setDevTestScore(res.devTestScore);
                        setUserLanguageDTOS(res.userLanguageDTOS);
                        setUserInterestFieldDTOS(res.userInterestFieldDTOS);
                        setUserPersonalityDTOS(res.userPersonalityDTOS);
                        setUserStudyFieldDTOS(res.setUserStudyFieldDTOS);
                });

                } catch (error) {
                    console.error("Failed to fetch data: ", error);
                }
            };
        }
    )



    return (
        <div className={styles.default}>
            <Header />
            <Sidebar/>
            <div className={styles.inner}>
                <div className={styles.body}>
                    <div>{name} {selfIntro} {bojRank} {dguEmail} {userRank} {teamExpCount} {leaderCount} {devTestScore} {userLanguageDTOS} {userInterestFieldDTOS} {userPersonalityDTOS} {userStudyFieldDTOS}</div>
                </div>
            </div>
        </div>
    );
}