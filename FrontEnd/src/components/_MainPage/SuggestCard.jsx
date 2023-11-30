import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';

const TeamCard = ({name, title, info, recruitPostId}) => {

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
        <div className={styles.SuggetCard}>
            <div className={styles.title}><Link to={`/post/${recruitPostId}`}>
                {title} {imoArray[num]}</Link> </div>
                 <div>
                {info.name} {info.bojRank} 
                {info.donggaeRank} {name}</div>
        </div>
    </>
    );
};

export default TeamCard;