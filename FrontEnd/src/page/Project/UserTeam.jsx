import React, { useEffect, useState } from 'react';
import styles from "./UserTeam.module.css"
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_UserTeam/TeamCard';

export default function UserTeam() {
    const [teambyL, setTeamL] = useState([]);
    const [teambyM, setTeamM] = useState([]);
    let token = localStorage.getItem('token') || '';
    localStorage.setItem('checkPost', JSON.stringify(''))
    
    useEffect(() => {
        const fetchData = async() => {
            try {
                fetch('/team/show', {
                    method: 'GET', // 사용하는 HTTP 메서드 (GET, POST, PUT 등)
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    setTeamL(res.teamByLeaders);
                    setTeamM(res.teamByMembers);
                    console.log(res)
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
                        {teambyL.map(data => <TeamCard leader = {true} team = {data} name={data.teamName} title={data.title} member={data.teamMemberPreview} recruitPost = {data.recruitPostId} teamId = {data.teamId}/>)} 
                    </div>
                </div>
                <div className={styles.box__}>
                <div className={styles.text__1} >팀원으로 속한 팀</div>
                    <div className={styles.formGroup}>
                        {teambyM.map(data => <TeamCard leader = {false} name={data.teamName} title={data.title} member={data.teamMemberPreview} teamId = {data.teamId}/>)} 
                    </div>
                </div>
                </div>
            </div>
        </div>
    );
}