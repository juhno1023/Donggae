import styles from "./Recruit.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_MainPage/TeamCard';
import UserCard from '../../components/_MainPage/UserCard';

export default function Recruit() {

    return (
        <div className={styles.default}>
                <Header />
                <Sidebar/>
                <div className={styles.inner}>
                    <div className={styles.body}>
                    <div className={styles.box__}>
                        <div className={styles.first_box}>
                            <div className={styles.title_text}>함께듣는 강의를 위한 동개를 모집 하고 있어요!</div>
                            <div className={styles.text__1} >추천 동개</div>
                            <div className={styles.formGroup}>
                            </div>
                        </div>
                        <div className={styles.second_box}>
                        <div className={styles.title_text}>대외적으로 프로젝트 진행을 위한 동개를 모집 하고 있어요!</div>
                        <div className={styles.text__1} >요즘 핫한 프로젝트</div>
                            <div className={styles.formGroup}>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
    );
}
