import React, { useState, useEffect } from 'react';
import styles from "./Rankpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import Rank from '../../components/_Tool/Rank';
import tearImg from '../../image/tearInfo.png';




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
                <div className={styles.text__1}>동개 랭킹</div>
                <img className={styles.tearImg} src={tearImg} alt="Donggae Logo" />
                <div className={styles.leaderboard}>
                    <div className={styles.ribbon}></div>
                    <table>
                        <tr>
                            <td>종합순위</td>
                            <td>동개등급</td>
                            <td>id</td>
                            <td>점수</td>
                            <td>tier</td>
                        </tr>
                    {number.map(data => <Rank  rankNo={data.ranking} rank={data.rankName} id={data.githubName} 
                    score={data.score} tier={data.bojRank}/>)}
                    </table>
                </div>
                </div>
            </div>
        </div>
    );
}