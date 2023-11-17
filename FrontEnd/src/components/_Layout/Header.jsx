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
                    <Link to='/main'><img className={styles.logoimg} src={bgImg} alt="Donggae Logo" /></Link>
                    <div>
                        <div className={styles.title}>Donggae</div>
                        <div className={styles.subtitle}><b>동</b>국대 <b>개</b>발자들</div>
                    </div>
                </div>
                <nav className={styles.navigation}>
                    <ul>
                    <li><Link to='/main'>메인</Link></li>
                    <li><Link to='/posting'>posting</Link></li>
                    <li><Link to='/post'>post</Link></li>
                    <li><Link to='/leader'>leader</Link></li>
                    </ul>
                </nav>
            </div>
        </div>
    );
}
