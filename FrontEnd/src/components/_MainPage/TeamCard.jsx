import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';
import Bronze2 from '../../image/Bronze2.svg';
import Silver2 from '../../image/Silver2.svg';
import Unrated from '../../image/Unrated.svg';

const TeamCard = ({ lecture, title, name, date, rank, donggaerank, language, recruitPostId}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    const [color, setColor] = useState('#000000');
    const rankMap = {
        'Bronze_II': Bronze2,
        'Silver_II': Silver2,
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