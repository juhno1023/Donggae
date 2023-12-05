import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';

const TeamCard = ({ lecture, title, name, date, rank, language, recruitPostId}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    const [color, setColor] = useState('#000000');
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
            <div className={styles.UserName_}>{name}{rank}</div>
            <div className={styles.UserCateInfo}>{date}</div>
        </div>
    </>
    );
};

export default TeamCard;