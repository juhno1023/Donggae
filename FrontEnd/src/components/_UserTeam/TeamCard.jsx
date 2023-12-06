import styles from './TeamCard.module.css';
import { Link } from 'react-router-dom';
import DongD from '../../image/DongDonggae.png';
import BronzeD from '../../image/BronzeDonggae.png';
import SilverD from '../../image/SilverDonggae.png';
import GoldD from '../../image/GoldDonggae.png';
import DiamondD from '../../image/DiamondDonggae.png';
import Bronze2 from '../../image/Bronze2.svg';
import Silver2 from '../../image/Silver2.svg';
import Unrated from '../../image/Unrated.svg';

const selectImage = (condition) => {
    // 조건에 따라 다른 이미지를 선택하는 함수
    if (condition === '다이아동개') {
      return DiamondD;
    } 
    else if (condition === '황금동개') {
        return GoldD;
    } 
    else if (condition === '은동개') {
        return SilverD;
    }
    else if (condition === '동동개') {
        return BronzeD;
    }
    else {
        return DongD;
    };

}

const TeamCard = ({ leader, name, title, member, recruitPost, teamId}) => {

    const imoArray= ["❤️", `🧡`, `💛`, `💚`, `💙`, `🩵`, `💜`, `🩷`, `🤎`, `🖤`, `🖤`, `🩶`, `🤍`, `💞`, `💟`, `💕`, `❣️`, `💝`, `💌`,`😀`, `😁`, `😃`, `😄`, `😋`, `😊`, `😉`, `😍`, `😘`, `🥰`, `😗`, `😙`, `🥲`, `🤗`, `🙂`, `☺️`, `😚`, `😐`, `😑`, `😶`, `🫥`, `😮`, `😯`, `😝`, `👻`, `😺`, `😸`, `😹`, `😻`, `😼`, `😽`, `🐱`]
    const num = Math.round(Math.random() * 50);

    let token = localStorage.getItem('token') || '';
    let checkPost = localStorage.getItem('checkPost') || '';

    const imageMap = {
        '다이아동개': DiamondD,
        '황금동개': GoldD,
        '은동개': SilverD,
        '동동개': BronzeD,
      };
    const rankMap = {
        'Bronze_II': Bronze2,
        'Silver_II': Silver2,
        'Unrated': Unrated,
    };


    const rankImg = (condition) => rankMap[condition];
    const selectImage = (condition) => imageMap[condition] || DongD;

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
                    <div className={styles.flex}> 
                        <img className={styles.profileImg} src={member.userProfile}/>
                        <div>
                        <img
                            className={styles.donggae_icon}
                            src={selectImage(member.donggaeRank)}
                            alt="Rank"
                        /> 
                        <img
                            className={styles.rankImg}
                            src={rankImg(member.bojRank)}
                            alt="Rank"
                        /> 
                        <span className={styles.name}>{member.name}</span>
                        </div>
                    </div> 
                    {imoArray[num]}{name}{imoArray[num]}
                    <div className={styles.title}><Link to={`/leader/${teamId}`}>
                    {title}</Link></div> 
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
                <div className={styles.flex}> 
                    <img className={styles.profileImg} src={member.userProfile}/>
                    <div>
                    <img
                        className={styles.donggae_icon}
                        src={selectImage(member.donggaeRank)}
                        alt="Rank"
                    /> 
                    <img
                        className={styles.rankImg}
                        src={rankImg(member.bojRank)}
                        alt="Rank"
                    /> 
                    <span className={styles.name}>{member.name}</span>
                    </div>
                </div> 
                    {imoArray[num]}{name}{imoArray[num]}
                    <div className={styles.title}><Link to={`/TeamInfo/${teamId}`}>
                    {title}</Link></div> 
                </div>
            </>
        );
    }
};

export default TeamCard;