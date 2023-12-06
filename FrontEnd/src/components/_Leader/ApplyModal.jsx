import styles from './ApplyModal.module.css';
import UserCard from '../_Card/UserCard';

function ApplyModal({setModalOpen, content, selfIntro, userInfo, language, interest, personal}) {
    // 모달 끄기 
    const closeModal = () => {
        setModalOpen(false);
    };
    return (
        <div className={styles.background}>
            <div className={styles.container}>
                <button className={styles.close} onClick={closeModal}>
                    X
                </button>
                <UserCard 
                        userId={userInfo.userId}
                        name={userInfo.githubName} 
                        selfIntro ={selfIntro}
                        content ={content}
                        intro={userInfo.intro} 
                        devTestScore={userInfo.devTestScore} 
                        rank={userInfo.bojRank} 
                        donggaeRank ={userInfo.userRank} 
                        language={language} 
                        interest={interest} 
                        personal={personal} 
                        study={userInfo.userStudyFields} 
                        userProfile={userInfo.userProfile} 
                        isPj ={false}
                    />
            </div>
        </div>
    );
}
export default ApplyModal;