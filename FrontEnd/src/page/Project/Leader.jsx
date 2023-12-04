import React, { useState ,useEffect } from 'react';
import styles from "./Leader.module.css"
import Header from "../../components/_Layout/Header";
import Deletion from '../../components/_Leader/Deletion';
import Selection from '../../components/_Leader/Selection';
import Sidebar from "../../components/_Layout/Sidebars";
import { useParams } from 'react-router-dom';

export default function Leader() {
    const [team, setTeam] = useState([]);
    const [teamData, setTeamData] = useState([]);
    const [applyData, setApplyData] = useState([]);
    let token = localStorage.getItem('token') || '';
    let { teamId } = useParams();

    const teamName = '';
    useEffect(() => {
        const handleClick = () => {
            try {
                fetch('http://localhost:8080/team/detail/leader', {
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
                        setTeam(res)
                        setTeamData(res.teamMemberList);
                        console.log(res)
                        setApplyData(res.applyMemberList);
                });

                } catch (error) {
                    console.error("Failed to fetch team data: ", error);
                }
            };
          const checkToken = () => {
            const token = localStorage.getItem("token");
            alert(token)
          }
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
                        {teamData.map(data => <Deletion name={data.name} rank={data.donggaeRank} id={data.userId}  value="추방"/>)}
                        </div>
                    </div>
                </div>
                <div className={styles.half}>
                    <div className={styles.text__1}>지원자 리스트</div>
                    <div className={styles.listed}>
                        <div className={styles.line}>
                        {applyData.map(data => <Selection name={data.name} rank={data.donggaeRank} id={data.userId}  value={["추가", "지원서 보기"]}/>)}
                        </div>
                </div>
                </div>
                </div>
            </div>
        </div>
    </div>
);
}