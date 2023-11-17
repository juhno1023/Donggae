import styles from "./Mainpage.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";

export default function Home() {
    const [checkedItems, setCheckedItems] = useState([]);
    const [teamData, setTeamData] = useState([]);
    let token = localStorage.getItem('token') || '';

    useEffect(() => {
        const fetchData = async() => {
            try {
                const response = await fetch('http://localhost:8080/recruitPost/recommend', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    console.log(res)
                });
                console.log(response.status)
                if (response.status === 400) {
                    alert(`400 Error`);
                    return;
                }else if(response.status === 404){
                    alert(`404 Error`);
                    return;
                }
                // const data = await response.json();
                // console.log(data);
                // setTeamData(data);
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
    }, []);
    // const user = {
    //     token: localStorage.getItem('token')
    // };
    // console.log(user.token);
   
    // let token = localStorage.getItem('token') || '';
    // useEffect(() => {
    //     const fetchData = async() => {
    //         try {
    //             fetch('http://localhost:8080/recruitPost/recommend', {
    //                 method: 'POST', // 사용하는 HTTP 메서드 (GET, POST, PUT 등)
    //                 headers: {
    //                     'Content-Type': 'application/json',
    //                     'Authorization': `Bearer ${token}`
    //                 },
    //             })
    //             .then(res=>res.json())        
    //             .then(res=> {
    //                 console.log(res)
    //             });
    //         } catch (error) {
    //             console.error("Failed to fetch: ", error);
    //         }
    //     };
    //     fetchData();   
    // }, []);


  return (
    <div className={styles.default}>
        <Header />
        <Sidebar/>
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                
            </div>
            <div className={styles.box__}>
                <div className={styles.half}>
                    <div className={styles.text__1}>현재 팀원 리스트</div>
                    <div className={styles.listed}>
                        <div className={styles.line}>
                        {teamData.map(data => <div>팀원 : {data.name} {data.donggaeRank}</div>)}
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
);
}