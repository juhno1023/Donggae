import styles from './TeamCard.module.css';
import { Link } from 'react-router-dom';

const TeamCard = ({ name, title, member, recruitPost}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);
    let checkEdit = "true"
    localStorage.setItem("checkEdit", checkEdit)
    console.log("checkEdit", checkEdit)
    return (
        <> 
            <div className={styles.GroupCard}>
                {imoArray[num]}{name}{imoArray[num]}
                <div>{title}</div>
                <div>{member.name}</div>
                <Link to={`/post/${recruitPost}`}>
                    <button  type="submit" className={styles.modifyBtn}>수정 및 삭제</button>
                </Link>
            </div>
        </>
    );
};

export default TeamCard;