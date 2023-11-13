import { useNavigate } from "react-router-dom";
import React, { useState  } from 'react';
import styles from "./Leader.module.css"
import Header from "../../components/_Layout/Header";
import bgImg from '../../image/donggae.png';
import AppliList from '../../components/AppliList';
import Sidebar from "../../components/_Layout/Sidebar";

export default function Leader() {
    const [checkedItems, setCheckedItems] = useState([])
    const datas = [
       { id: "day6", title: "00", completed: false, },
      { id: "youngK", title: "01", completed: true },
      { id: "pill", title: "02", completed: false }
    ]
    const datas2 = [
        { id: "d", title: "HTML", completed: false, },
       { id: "s", title: "CSS", completed: true },
       { id: "a", title: "Javascript", completed: false }
     ]
    const checkedItemHandler = (code, isChecked) => {
        if (isChecked) { //체크 추가할때
            setCheckedItems([...checkedItems, code])
        } else if (!isChecked && checkedItems.find(one => one === code)) {//체크 해제할때 checkedItems에 있을 경우
            const filter = checkedItems.filter(one => one !== code)
            setCheckedItems([...filter])
        }
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form Data:', checkedItems);
    };


  return (
    <div className={styles.default}>
        <Header />
        <Sidebar/>
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                <div  className={styles.text__1} for="team_name" >🧡떡잎방범대🧡이번 학기에 소공 같이 플젝 하실 분 모집합니다</div>
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
                        {datas.map(data => <AppliList title={data.title} id={data.id} checkedItems={checkedItems} checkedItemHandler={checkedItemHandler} />)}
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