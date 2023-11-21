import styles from './TeamCard.module.css';

const TeamCard = ({ name, title, member}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    console.log(num)

    return (
        <> 
            <div className={styles.GroupCard}>
                {imoArray[num]}{name}{imoArray[num]}
                <div>{title}</div>
                <div>{member.name}</div>
                <div></div>
            </div>
        </>
    );
};

export default TeamCard;