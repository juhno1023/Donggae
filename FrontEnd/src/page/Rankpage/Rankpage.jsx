import React, { useState, useEffect } from 'react';
import styles from "./Rankpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";
import Rank from '../../components/Rank';

export default function Mypage() {
    const history = useNavigate();

    const[rankNo, setRankNo] = useState();
    const[rank, setRank] = useState();
    const[id, setId] = useState();
    const[field, setField] = useState([]);
    const[bojrank, setBojrank] = useState();
    const[score, setScore] = useState();
    
    

    let token = localStorage.getItem('token') || '';

    useEffect(() => {
        const handleClick = () => {
            try {
                fetch('http://localhost:8080/members/rank', {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    }).then(res=>res.json())        
                        .then(res=> {
                        console.log(res)
                        setRankNo(res.ranking);
                        setRank(res.rankName);
                        setId(res.githubName);
                        setField(res.userInterestFields);
                        setScore(res.score);
                        setBojrank(res.bojRank);
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
                <div className={styles.box__}>
                    <div className={styles.rankBox}>
                        <Rank rankNo={rankNo} rank={rank} id={id} field={field} score={score} tier={bojrank}></Rank>
                    </div>
                </div>
                </div>
            </div>
        </div>
    );
}