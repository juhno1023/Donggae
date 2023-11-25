import styles from "./Header.module.css"
import { useNavigate , Link} from "react-router-dom";
import bgImg from '../../image/donggae.png';
import { useState } from 'react';

export default function Header() {


    return (
        <div className={styles.header}>
            <div className={styles.contents}>
                <div className={styles.logo}>
                    <Link to='/main'><img className={styles.logoimg} src={bgImg} alt="Donggae Logo" /></Link>
                    <div>
                        <div className={styles.title}>Donggae</div>
                        <div className={styles.subtitle}><b>동</b>국대 <b>개</b>발자들</div>
                    </div>
                    <div className={styles.search}>
                        <input type="text" placeholder="검색어 입력"/>
                        <img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"/>
                    </div>
                </div>
                <nav className={styles.navigation}>
                    <ul>
                    <li><Link to='/main'>메인</Link></li>
                    <li><Link to='/posting'>posting</Link></li>
                    <li><Link to='/leader'>leader</Link></li>
                    <li><Link to='/userteam'>myteam</Link></li>
                    <li><Link to='/rank'>rank</Link></li>
                    </ul>
                </nav>
            </div>
        </div>
    );
}
