import React, { useEffect, useState } from 'react';
import styles from "./UserTeam.module.css"
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";

export default function UserTeam() {
    const [teamList, setTeamList] = useState([]);
    const user = {
        token: localStorage.getItem('token')
    };
    console.log(user.token);
   
    let token = localStorage.getItem('token') || '';
    useEffect(() => {
        const fetchData = async() => {
            try {
                fetch('http://localhost:8080/team/show', {
                    method: 'POST', // 사용하는 HTTP 메서드 (GET, POST, PUT 등)
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    console.log(res)
                    setTeamList(res.teamMemberList);
                });
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
                    <div className={styles.text__1} >팀장으로 속한 팀</div>
                    <div className={styles.formGroup}>
                        <div className={styles.GroupCard}>
                            💙우주최강팀💙
                            <div>동국대 졸업할 수 있을까</div>
                            <div>팀장</div>
                            <div>상세보기</div>
                        </div>
                    </div>
                </div>
                <div className={styles.box__}>
                <div className={styles.text__1} >팀원으로 속한 팀</div>
                    <div className={styles.formGroup}>
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
);
}