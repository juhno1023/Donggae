import styles from './Card.module.css';

const TeamCard = ({ lecture, title, name, date, rank, language}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    console.log(num)
    if(!lecture) lecture = "개인"
    return (
        <> 
            <div className={styles.GroupCard}>
                {lecture}
                
                <div>{imoArray[num]}{title}{imoArray[num]}</div>
                <div>{rank}{name}</div>
                <div>{date}</div>
                {language}
            </div>
        </>
    );
};

export default TeamCard;