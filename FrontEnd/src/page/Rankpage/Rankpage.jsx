import React, { useState, useEffect } from 'react';
import styles from "./Rankpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";
import Rank from '../../components/Rank';

export default function Mypage() {
    const history = useNavigate();

    const[rank, setRank] = useState();
    const[id, setId] = useState();
    const[field, setField] = useState([]);
    const[bojrank, setBojrank] = useState();
    

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
                        setRank(res.rankName);
                        setId(res.githubName);
                        setField(res.userInterestFields);
                        setBojrank(res.bojRank);
                });

                } catch (error) {
                    console.error("Failed to fetch team data: ", error);
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
                    <div>
                        <Rank rankNo={1} rank={rank} id={id} field={field} tier={bojrank}></Rank>
                    </div>
                </div>
                </div>
            </div>
        </div>
    );
}