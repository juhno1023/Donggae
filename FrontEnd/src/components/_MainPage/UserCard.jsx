import styles from './Card.module.css';

const TeamCard = ({ name, intro, devTestScore, rank, language, interest, personal, study}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    console.log(num)
    return (
        <> 
            <div className={styles.UserCard}>                
                <div>{name}</div>
                <div>{intro}{devTestScore}{rank}</div>
                <div>#{language}#{interest}#{personal},#{study}</div>
            </div>
        </>
    );
};

export default TeamCard;