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
                const res = await fetch('/recruitPost/recommend', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })     
                .then(res=> {
                    if (res.status === 400) {
                        alert(`400 Error`);
                        return;
                    }
                    if(res.status === 404){
                        alert(`포스트 영개`);
                        return;
                    }
                    return res.json();
                }).then(data => {
                    console.log(data)
                })
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
    }, []);


  return (
    <div className={styles.default}>
            <Header />
            <Sidebar/>
            <div className={styles.inner}>
                <div className={styles.body}>
                <div className={styles.box__}>
                    <div>
                        <div className={styles.text__1} >팀장으로 속한 팀</div>
                        <div className={styles.formGroup__}>
                            <div className={styles.GroupCard}>
                                💙우주최강팀💙
                                <div>동국대 졸업할 수 있을까</div>
                                <div>팀장</div>
                                <div>상세보기</div>
                            </div>
                        </div>
                    </div>
                    <div>
                    <div className={styles.text__1} >팀원으로 속한 팀</div>
                        <div className={styles.formGroup}>
                            <div className={styles.GroupCard}>
                                💙우주최강팀💙
                                <div>동국대 졸업할 수 있을까</div>
                                <div>팀장</div>
                                <div>상세보기</div>
                            </div>
                            <div className={styles.GroupCard}>
                                💙우주최강팀💙
                                <div>동국대 졸업할 수 있을까</div>
                                <div>팀장</div>
                                <div>상세보기</div>
                            </div>
                            <div className={styles.GroupCard}>
                                💙우주최강팀💙
                                <div>동국대 졸업할 수 있을까</div>
                                <div>팀장</div>
                                <div>상세보기</div>
                            </div>
                            <div className={styles.GroupCard}>
                                💙우주최강팀💙
                                <div>동국대 졸업할 수 있을까</div>
                                <div>팀장</div>
                                <div>상세보기</div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
);
}