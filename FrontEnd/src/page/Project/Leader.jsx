import { useNavigate } from "react-router-dom";
import React, { useState ,useEffect } from 'react';
import styles from "./Leader.module.css"
import Header from "../../components/_Layout/Header";
import bgImg from '../../image/donggae.png';
import AppliList from '../../components/AppliList';
import Sidebar from "../../components/_Layout/Sidebar";

export default function Leader() {
    const datas2 = [
        { id: "d", title: "HTML", completed: false, },
       { id: "s", title: "CSS", completed: true },
       { id: "a", title: "Javascript", completed: false }
     ]
     const [checkedItems, setCheckedItems] = useState([]);
     const [teamData, setTeamData] = useState([]);
 
     const userteamId = {
         // teamId: sessionStorage.getItem('teamId')
         // 세션 스토리지가 아니라 URL로 받아오는 것 : 팀 상세보기 클릭 후 나오는 화면이기 때문에
         // 임시로 2라 설정함
         teamId: 2
     };
     console.log(userteamId.teamId);
 
     useEffect(() => {
         const fetchData = async() => {
             try {
                 const response = await fetch('http://localhost:8080/team/detail/member', {
                     method : "POST",
                     headers: {
                         'Content-Type': 'application/json',
                     },
                     body: JSON.stringify({
                         teamId: userteamId.teamId
                     })
                 });
                 console.log("Response status:", response.status);
                 const dd = await response.json();
                     console.log(dd)
                     setTeamData(dd.items)
 
             } catch (error) {
                 console.error("Failed to fetch team data: ", error);
             }
         };
         fetchData();   
     }, []);
 
     const checkedItemHandler = (code, isChecked) => {
         if (isChecked) { //체크 추가할때
             setCheckedItems([...checkedItems, code])
         } else if (!isChecked && checkedItems.find(one => one === code)) {//체크 해제할때 checkedItems에 있을 경우
             const filter = checkedItems.filter(one => one !== code)
             setCheckedItems([...filter])
         }
     }


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
                        {teamData.map(data => <AppliList title={data.title} teamName={data.teamName} checkedItems={checkedItems} checkedItemHandler={checkedItemHandler} />)}
                        </div>
                    </div>
                </div>
                <div className={styles.half}>
                    <div className={styles.text__1}>지원자 리스트</div>
                    <div className={styles.listed}>
                        <div className={styles.line}>
                        {datas2.map(data => <AppliList title={data.title} id={data.id} checkedItems={checkedItems} checkedItemHandler={checkedItemHandler} />)}
                        </div>
                </div>
                </div>
                </div>
            </div>
        </div>
    </div>
);
}