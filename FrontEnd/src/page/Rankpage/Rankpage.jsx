import React, { useState, useEffect } from 'react';
import styles from "./Rankpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";
import Rank from '../../components/Rank';

export default function Mypage() {
    const history = useNavigate();


    const[number, setNumber] = useState([]);
    
    

    let token = localStorage.getItem('token') || '';

    useEffect(() => {
        const handleClick = async() => {
            try {
                fetch('http://localhost:8080/members/rank', {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    }).then(res=>res.json())        
                    .then(res=> {
                    console.log(res);
                    setNumber(res);
                
            });

                } catch (error) {
                    console.error("Failed to fetch data: ", error);
                }
            };
            handleClick();
        },[]);

    return (
        <div className={styles.default}>
            <Header />
            <Sidebar/>
            <div className={styles.inner}>
                <div className={styles.body}>
                <div className={styles.box__}>
                    <div className={styles.rankBox}>
                    {number.map(data => <Rank  rankNo={data.ranking} rank={data.rankName} id={data.githubName} 
                    field={data.userInterestField} score={data.score} tier={data.bojRank}/>)}
                    </div>
                </div>
                </div>
            </div>
        </div>
    );
}