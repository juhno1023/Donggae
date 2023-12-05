import styles from "./Evaluation.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";

export default function Home() { 

    let token = localStorage.getItem('token') || '';

    const [testInfo, setTestInfo] = useState([]);

    useEffect(() => {
        const fetchData = async() => {
            try {
                fetch('/testpage', {
                    method: 'GET', 
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    console.log(res);
                    setTestInfo(res);
                });
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };

        fetchData();   
        
    }, []);

    return(
        <div className={styles.default}>
        <Header />
        <Sidebar/>
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                <p className={styles.title_text}>역량 평가</p>

                
            </div>
            </div>
        </div>
        </div>
    );
}