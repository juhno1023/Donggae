import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';
import Bronze2 from '../../image/Bronze2.svg';
import Silver2 from '../../image/Silver2.svg';
import Silver1 from '../../image/Silver1.svg';
import Gold5 from '../../image/Gold5.svg';
import Unrated from '../../image/Unrated.svg';

import DongD from '../../image/DongDonggae.png';
import BronzeD from '../../image/BronzeDonggae.png';
import SilverD from '../../image/SilverDonggae.png';
import GoldD from '../../image/GoldDonggae.png';
import DiamondD from '../../image/DiamondDonggae.png';

const selectImage = (condition) => {
    // 조건에 따라 다른 이미지를 선택하는 함수
    if (condition === '다이아동개') {
      return DiamondD;
    } 
    else if (condition === '황금동개') {
        return GoldD;
    } 
    else if (condition === '은동개') {
        return SilverD;
    }
    else if (condition === '동동개') {
        return BronzeD;
    }
    else {
        return DongD;
    };

}

const TeamCard = ({ lecture, title, name, date, rank, donggaeRank, language, recruitPostId}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    const [color, setColor] = useState('#000000');
    const rankMap = {
        'Bronze_II': Bronze2,
        'Silver_II': Silver2,
        'Silver_I': Silver1,
        'Gold_V' : Gold5,
        'Unrated': Unrated,
    };
    const rankImg = (condition) => rankMap[condition];


    useEffect(() => {
        function getRandomColor(){
            return '#'+Math.floor(Math.random()*16777215).toString(16);
        }
        setColor(getRandomColor());
    }, []);

    return (
    <>
        <div className={styles.GroupCard}>
        <span className={styles.Lecture} style={{ backgroundColor: !lecture ? "black" : color }}>{lecture || "개인"}</span>
            <div className={styles.title}><Link to={`/post/${recruitPostId}`}>
                {title}</Link></div>  
                {language ? (
                    language.map((data, index) => (
                    <span key={index} className={styles.tag}>
                        {data}
                    </span>
                    ))
                ) : null}
            <div className={styles.UserName_}>
                <img
                    className={styles.donggae_icon}
                    src={selectImage(donggaeRank)}
                    alt="Rank"
                /> 
            <img
                className={styles.rankImg}
                src={rankImg(rank)}
                alt="Rank"
            /> 
                {name}</div>
            <div className={styles.UserCateInfo}>{date}</div>
        </div>
    </>
    );
};

export default TeamCard;