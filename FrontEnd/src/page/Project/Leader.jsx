import React, { useState ,useEffect } from 'react';
import styles from "./Leader.module.css"
import Header from "../../components/_Layout/Header";
import Deletion from '../../components/_Leader/Deletion';
import Selection from '../../components/_Leader/Selection';
import Sidebar from "../../components/_Layout/Sidebar";

export default function Leader() {
    const datas2 = [
        { id: "d", title: "HTML", completed: false, },
       { id: "s", title: "CSS", completed: true },
       { id: "a", title: "Javascript", completed: false }
    ]
    const [teamData, setTeamData] = useState([]);
    const [applyData, setApplyData] = useState([]);
    let token = localStorage.getItem('token') || '';

    const user = {
        // teamId: sessionStorage.getItem('teamId')
        // 세션 스토리지가 아니라 URL로 받아오는 것 : 팀 상세보기 클릭 후 나오는 화면이기 때문에
        // 임시로 2라 설정함
        teamId: 1,
    };

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
                        teamId: user.teamId,
                    }),
                    }).then(res=>res.json())        
                        .then(res=> {
                        setTeamData(res.teamMemberList);
                        console.log(teamData[0])
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
                <div  className={styles.text__1} >🧡떡잎방범대🧡이번 학기에 소공 같이 플젝 하실 분 모집합니다</div>
                <div className={styles.formGroup}>
                    <div>이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs 입니다
                        </div>
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