import React, { useState } from 'react';
import styles from "./Mypage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";

export default function Mypage() {
    const history = useNavigate();

    return (
        <div className={styles.default}>
            <Header />
            <Sidebar/>
            <div className={styles.inner}>
                <div className={styles.body}>
                    <div>으악 어딨어</div>
                </div>
            </div>
        </div>
    );
}