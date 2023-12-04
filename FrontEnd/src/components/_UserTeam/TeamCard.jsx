import styles from './TeamCard.module.css';
import { Link } from 'react-router-dom';

const TeamCard = ({ leader, name, title, member, recruitPost, teamId}) => {

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
    const Complete = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`/recruitPost/complete`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },body: JSON.stringify({
                    teamId: teamId,
                }),
            })
            if (response.ok) {
                alert("마감 완료");
                window.location.replace("/userteam");
            } else if (response.status === 400) {
                const errorText = await response.text();
                alert(`일치하지 않습니다. ${errorText}`);
                console.error("마감 실패 : ", errorText);
            } else {
                console.error("마감 실패 : ", response.statusText);
            }
            
        } catch (error) {
            console.error("fatch to fail : ", error);
        }
    };
    if(leader){
        return (
            <> 
                <div className={styles.GroupCard}>
                    {imoArray[num]}{name}{imoArray[num]}
                    <div className={styles.title}><Link to={`/leader/${teamId}`}>
                    {title}</Link></div> 
                    <div>{member.name}</div>
                    <button  type="submit" className={styles.modifyBtn} onClick={checkModify}>
                        <Link to={`/post/${recruitPost}`}>수정</Link>
                    </button>
                    <button  type="submit" className={styles.modifyBtn} onClick={Deletion}>
                        <Link to={`/main`}>삭제</Link>
                    </button>
                    <button  type="submit" className={styles.completeBtn} onClick={Complete}>
                        <Link to={`/main`}>마감하기</Link>
                    </button>
                </div>
            </>
        );
    }
    else{
        return (
            <> 
                <div className={styles.GroupCard}>
                    {imoArray[num]}{name}{imoArray[num]}
                    <div className={styles.title}><Link to={`/teaminfo/${teamId}`}>
                    {teamId}{title}</Link></div> 
                    <div>{member.name}</div>
                    <button  type="submit" className={styles.modifyBtn} onClick={checkModify}>
                        <Link to={`/post/${recruitPost}`}>수정</Link>
                    </button>
                    <button  type="submit" className={styles.modifyBtn} onClick={Deletion}>
                        <Link to={`/main`}>삭제</Link>
                    </button>
                    <button  type="submit" className={styles.completeBtn} onClick={Complete}>
                        <Link to={`/main`}>마감하기</Link>
                    </button>
                </div>
            </>
        );
    }
};

export default TeamCard;