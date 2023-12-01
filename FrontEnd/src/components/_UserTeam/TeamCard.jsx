import styles from './TeamCard.module.css';
import { Link } from 'react-router-dom';

const TeamCard = ({ name, title, member, recruitPost}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);

    let token = localStorage.getItem('token') || '';
    let checkPost = localStorage.getItem('checkPost') || '';
    const checkModify = () =>{
        localStorage.setItem('checkPost', JSON.stringify('modify'))
        console.log("checkPost :", checkPost)
    }
    const Deletion = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`/recruitPost/${recruitPost}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
            })
            if (response.ok) {
                alert("삭제 완료");
                window.location.replace("/userteam");
            } else if (response.status === 400) {
                const errorText = await response.text();
                alert(`일치하지 않습니다. ${errorText}`);
                console.error("삭제 실패 : ", errorText);
            } else {
                console.error("삭제 실패 : ", response.statusText);
            }
            
        } catch (error) {
            console.error("fatch to fail : ", error);
        }
    };

    return (
        <> 
            <div className={styles.GroupCard}>
                {imoArray[num]}{name}{imoArray[num]}
                <div>{title}</div>
                <div>{member.name}</div>
                <button  type="submit" className={styles.modifyBtn} onClick={checkModify}>
                    <Link to={`/post/${recruitPost}`}>수정</Link>
                </button>
                <button  type="submit" className={styles.modifyBtn} onClick={Deletion}>
                    <Link to={`/main`}>삭제</Link>
                </button>
            </div>
        </>
    );
};

export default TeamCard;