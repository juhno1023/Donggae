import styles from "./Header.module.css"
import { useNavigate } from "react-router-dom";
import bgImg from '../../image/donggae.png';
import { Link } from 'react-router-dom';

export default function Header() {
    const history = useNavigate();
      
    return (
        <div className={styles.header}>
            <div className={styles.contents}>
                <div className={styles.logo}>
                    <Link to='/'><img className={styles.logoimg} src={bgImg} alt="Donggae Logo" /></Link>
                    <div>
                        <div className={styles.title}>Donggae</div>
                        <div className={styles.subtitle}><b>동</b>국대 <b>개</b>발자들</div>
                    </div>
                </div>
                <nav className={styles.navigation}>
                    <ul>
                    <li><Link to='/'>메인</Link></li>
                    <li><Link to='/posting'>랭킹</Link></li>
                    <li><Link to='/post'>팀 찾기</Link></li>
                    <li><Link to='/application'>팀원 모집</Link></li>
                    </ul>
                </nav>
            </div>
        </div>
    );
}
