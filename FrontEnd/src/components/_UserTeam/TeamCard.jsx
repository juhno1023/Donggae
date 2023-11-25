import styles from './TeamCard.module.css';
import { Link } from 'react-router-dom';

const TeamCard = ({ name, title, member, recruitPost}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    let checkEdit = recruitPost
    localStorage.setItem("checkEdit", JSON.stringify(checkEdit))

    return (
        <> 
            <div className={styles.GroupCard}>
                {imoArray[num]}{name}{imoArray[num]}
                <div>{title}</div>
                <div>{member.name}</div>
                <Link to={`/post/${recruitPost}`}>
                    <button  type="submit" className={styles.modifyBtn}>조회</button>
                </Link>
            </div>
        </>
    );
};

export default TeamCard;