import styles from "./Evaluation.module.css"
import React, { useEffect, useState} from 'react';
import {Link} from 'react-router-dom'
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";

import DataStructure from '../../image/DataStructure.png';
import Database from '../../image/Database.png';
import OperatingSystem from '../../image/OperatingSystem.png';

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


    const ImgSelect = (num) =>{
        if(num === 1)
            return DataStructure;
        else if(num === 2)
            return Database;
        else
            return OperatingSystem;
    }

    return(
        <div className={styles.default}>
        <Header />
        <Sidebar/>
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>

                <p className={styles.title_text}>역량 평가</p>
                {testInfo.length > 0 && (
                <div className={styles.test_box}>
                        {testInfo.map(item => (
                        <div className={styles.problem_box}>
                            <div className={styles.subject}>{item.testField}</div>
                            <img className={styles.i} src={ImgSelect(item.id)}/>
                            <Link className={styles.btn} to={`/evaluation/${item.id}`}><button type="submit" className={styles.submitBtn} onClick={()=>testInfo}>응시하기</button></Link>
                        </div>
                    ))}
                </div>
                 )}
            </div>
            </div>
        </div>
        </div>
    );
}