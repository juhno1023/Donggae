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
    var lecture = "개인"; // Assuming you want to set a default value if lecture is not defined

    return (
    <>
        <div className={styles.GroupCard}>
        <span className={styles.Lecture} style={{backgroundColor : color}}>{lecture}</span>
            <div className={styles.title}><Link to={`/post/${recruitPostId}`}>
                {title} {imoArray[num]}
                </Link></div>  
            <div>{rank}{name}</div>
            <div>{date}</div>
            {language}
        </div>
    </>
    );
};

export default TeamCard;