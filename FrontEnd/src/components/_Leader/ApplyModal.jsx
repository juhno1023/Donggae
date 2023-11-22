import styles from './ApplyModal.module.css';

function ApplyModal({setModalOpen, content, selfIntro, userInfo}) {
    // 모달 끄기 
    const closeModal = () => {
        setModalOpen(false);
    };
    //userinfo의 userInterest? Language, PersonalitiesDTO 넣어야함. (Selection에서)
    return (
        <div className={styles.background}>
            <div className={styles.container}>
                <button className={styles.close} onClick={closeModal}>
                    X
                </button>
                자기소개 : {content} <br/>
                지원동기 및 나의 역량 : {selfIntro}
            </div>
        </div>
    );
}
export default ApplyModal;