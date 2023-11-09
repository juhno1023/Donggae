import styles from "./Header.module.css"
import { useNavigate } from "react-router-dom";
import bgImg from '../../image/donggae.png';

export default function Header() {
    const history = useNavigate();
    const button_startLogIn = () => { history("/signin"); };
    const button_startSignUp = () => { history("/signup"); };

    const navigateToHome = () => {history("/");};
    
    return (
        <div className={styles.header}>
            <div className={styles.contents}>
                <div className={styles.logo}>
                    <img className={styles.logoimg} src={bgImg} alt="Donggae Logo" />
                    <div>
                        <div className={styles.title}>Donggae</div>
                        <div className={styles.subtitle}><b>동</b>국대 <b>개</b>발자들</div>
                    </div>
                </div>
                <nav className={styles.navigation}>
                    <ul>
                        <li>메인</li>
                        <li>랭킹</li>
                        <li>팀 찾기</li>
                        <li>팀원 모집</li>
                    </ul>
                </nav>
            </div>
        </div>
    );
}
