import React, { useEffect, useState } from 'react';
import styles from "./TeamInfo.module.css"
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import { useParams } from 'react-router-dom';

export default function TeamInfo() {
    const [team, setTeam] = useState([]);
    const [teamData, setTeamData] = useState([]);
    let token = localStorage.getItem('token') || '';
    let { teamId } = useParams();

    useEffect(() => {
        const handleClick = () => {
            try {
                fetch('http://localhost:8080/team/detail/member', {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify({
                        teamId: teamId,
                    }),
                    }).then(res=>res.json())        
                        .then(res=> {
                        console.log(res.teamMemberList)
                        setTeam(res)
                        setTeamData(res.teamMemberList);
                });
                } catch (error) {
                    console.error("Failed to fetch team data: ", error);
                }
            };
          handleClick();   
    }, []);



  return (
    <div className={styles.default}>
        <Header />
        <Sidebar/>
        
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                <div  className={styles.text__1} >🧡{team.teamName}🧡{team.title}</div>
                <div className={styles.formGroup}>
                    <div>{team.content}</div>
                </div>
            </div>
            <div className={styles.box__}>
                <div className={styles.half}>
                    <div className={styles.text__1}>현재 팀원 리스트</div>
                    <div className={styles.listed}>
                        <div className={styles.line}>
                        {teamData? teamData.map(data => (
                            <div>
                                {data.isLeader ? '팀장' : '팀원'}: {data.name} {data.donggaeRank}
                            </div>))
                        : null}
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
);
}